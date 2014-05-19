#! /usr/bin/python3
#-*- coding: utf-8 -*-
########  File Statistics  ###########

## statFile class works with a kind of file which result of
## bash : find /home/fs1 -print0 | xargs -0 stat > /home/fs1-files-statistics.txt
## command stat applied to all files and direcotries in a specified path

class statFile:
	
	def __init__(self,filename):
		self.fileName=filename
		print("INFO : _init_ : File to be analyzed is : {0}".format(self.fileName))
		
		if os.path.exists(self.fileName):
			print("OK : _init_ : File Exists.")			
		else:
			print("Error : _init_ : File Does NOT Exist.")
		
		
		
			
	def verifyFormat(self):
	
		## delete blank lines		
		## bash : sed -i '1s/^/\n/' test.txt   -> insert empty lines to the begining	
		## bash : sed '/^$/d' test.txt -> delete blank lines at begining
		## bash : awk 'NF' ->delete blank lines in all file
		os.system("awk 'NF' {0} > {0}.aux".format(self.fileName))			
		print("INFO : _verifyFormat_ : Delete blank lines")
		
		
		
		
		
		
		
		
		## verify if line number 0,8,16,24 ...x8 is the filename
		
		errors = 0
		NoFiles = 0
		self.TotalLines = 0
		
		## Total of lines
		
		##open aux file
		self.FName = open("{0}.aux".format(self.fileName))
		for i,line in enumerate(self.FName):
			self.TotalLines += 1 
		self.FName.close()
		
		
		
		self.FName = open("{0}.aux".format(self.fileName))		
		for i,line in enumerate(self.FName):
			#print("#line :{0} , module8 : {1}".format(i,i%8))
			## module 8 equal 0 is the line with name of file
			if i%8 == 0 : 
				if line.split()[0] != 'File:': ## [0] is "File:" word
					errors = errors +1
				else:					
					#print("\t{0}".format(line.split()[1])) ## name of file
					NoFiles += 1  ## Number of files 
		##close aux file
		self.FName.close()	
		
		
		if (self.TotalLines/NoFiles)%8 != 0:
			errors += 1
		
			
		if errors != 0:
			print("Error : _verifyFormat_ : File Does NOT Compliant With Structure Required")
		else:
			print("OK : _verifyFormat_ : File Compliant With Structure Required")
				

		return errors
		
		
		
		
		
	#generate sql script		
	def mysql_script(self):
		print("INFO : _mysql_script_ : creating mysql script ... ")
		self.FName = open("{0}.aux".format(self.fileName))		
		
		scriptFilename="{0}_script.sql".format(self.fileName.replace(".txt",""))
		os.system("echo "" > {0}".format(scriptFilename))
		scriptFile=open(scriptFilename,'w')
		
		
		file_attrs=[]
		filepath='';size_bytes=0;filetype='';accessDate='';modifyDate='';changeDate=''
		counter=0
		
		for i,line in enumerate(self.FName):
			if ((i%8 == 0 and i !=0) or i == (self.TotalLines-1)) : #Not insert when start reading file and insert when lastline is reached
								
				#filepath = file_attrs[0].split()[1][1:-1]
				filepath = file_attrs[0].replace("File: ","").replace("/home/sajidaustria","").replace("'","\\'")[3:-2]
				size_bytes=file_attrs[1].split()[1]
								
				try:
					file_attrs[1].split()[8]
					filetype=file_attrs[1].split()[7]+file_attrs[1].split()[8]
					#cat typeoffiles.txt | sort -u
					#directory
					#regularempty
					#regularfile
					#symboliclink
				except:
					filetype=file_attrs[1].split()[7]
				
				accessDate=file_attrs[4].split()[1]+' '+file_attrs[4].split()[2]
				modifyDate=file_attrs[5].split()[1]+' '+file_attrs[5].split()[2]
				changeDate=file_attrs[6].split()[1]+' '+file_attrs[6].split()[2]
				
				#print("\nInsert to table values ()")
				#print("FileName : {0}".format(filepath))
				#print("Size : {0}".format(size_bytes))
				#print("FileType : {0}".format(filetype))
				#print("Access : {0}".format(accessDate))
				#print("Modify : {0}".format(modifyDate))
				#print("Change : {0}".format(changeDate))
				
				scriptFile.write("insert ignore into filestats1_new (filepath,size_bytes,filetype,accessDate,modifyDate,changeDate) values ('{0}','{1}','{2}','{3}','{4}','{5}');\n".format(filepath,size_bytes,filetype,accessDate,modifyDate,changeDate))
				
				counter += 1
				if counter%1000 == 0:
					print("{0} Files processed".format(counter)) 
				
				#print(file_attrs)
				file_attrs.clear()  # next loop use empty list
						
			file_attrs.append(line)
			
				
		print("INFO : _mysql_script_ : script created !! ")		
		scriptFile.close()	
		self.FName.close()
	
	
	#generate CSV file
	def csv_file(self):
		print("INFO : _csv_file_ : creating csv file ... ")
		self.FName = open("{0}.aux".format(self.fileName))		
		
		scriptFilename="{0}.csv".format(self.fileName.replace(".txt",""))
		os.system("echo "" > {0}".format(scriptFilename))
		scriptFile=open(scriptFilename,'w')
		scriptFile.write("\"filepath\",\"size_bytes\",\"filetype\",\"accessDate\",\"modifyDate\",\"changeDate\"\n")
		
		file_attrs=[]
		filepath='';size_bytes=0;filetype='';accessDate='';modifyDate='';changeDate=''
		counter=0
		
		for i,line in enumerate(self.FName):
			if ((i%8 == 0 and i !=0) or i == (self.TotalLines-1)) : #Not insert when start reading file and insert when lastline is reached
								
				#filepath = file_attrs[0].split()[1][1:-1]
				filepath = file_attrs[0].replace("File: ","").replace("/home/sajidaustria","").replace("'","\\'")[3:-2]
				size_bytes=file_attrs[1].split()[1]
								
				try:
					file_attrs[1].split()[8]
					filetype=file_attrs[1].split()[7]+file_attrs[1].split()[8]
					#cat typeoffiles.txt | sort -u
					#directory
					#regularempty
					#regularfile
					#symboliclink
				except:
					filetype=file_attrs[1].split()[7]
				
				accessDate=file_attrs[4].split()[1]+' '+file_attrs[4].split()[2]
				modifyDate=file_attrs[5].split()[1]+' '+file_attrs[5].split()[2]
				changeDate=file_attrs[6].split()[1]+' '+file_attrs[6].split()[2]
				
				#print("\nInsert to table values ()")
				#print("FileName : {0}".format(filepath))
				#print("Size : {0}".format(size_bytes))
				#print("FileType : {0}".format(filetype))
				#print("Access : {0}".format(accessDate))
				#print("Modify : {0}".format(modifyDate))
				#print("Change : {0}".format(changeDate))
				
				scriptFile.write("\"{0}\",\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\"\n".format(filepath,size_bytes,filetype,accessDate,modifyDate,changeDate))
				
				counter += 1
				if counter%1000 == 0:
					print("{0} Files processed".format(counter)) 
				
				#print(file_attrs)
				file_attrs.clear()  # next loop use empty list
						
			file_attrs.append(line)
			
				
		print("INFO : _csv_file_ : csv file created !! ")		
		scriptFile.close()	
		self.FName.close()	
	
	
		
	def toMongoDB(self):
		print("to mongodb")

		
	




		
#Main
#--------------------------------
#if _name_ == '_main_':
import os
import sys
import os.path
import mmap

print("\nFile Statistics:::.....")

if len(sys.argv) == 2:
	myFile=statFile(sys.argv[1])
	if myFile.verifyFormat() == 0:
		print("INFO : _main : proceed to database activities")
		myFile.mysql_script()
		#myFile.csv_file()
	else:
		print("Error : _main_ : Not insert to DB")


	

else:
	print("Error : _main_ : Not arguments or more than one.")






