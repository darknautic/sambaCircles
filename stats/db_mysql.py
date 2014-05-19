#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# apt-get install python-mysql.connector
import MySQLdb as mdb
import sys

try:
    con = mdb.connect('localhost', 'root', 'qazwsx', 'test');

    cur = con.cursor()
    cur.execute("SELECT VERSION()")

    ver = cur.fetchone()
    
    print("Database version : {0}".format(ver))
    
except:
  
    #print "Error %d: %s" % (e.args[0],e.args[1])
    sys.exit(1)
    
finally:    
        
    if con:    
        con.close()
