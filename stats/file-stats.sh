#! /bin/bash 

## Files Statistics ##
##----------------------------------------


## FileShare paths
## list of all files in all file shares 

#find /home/sajidaustria/C0D1NG/file-stats/ -type d  -print0 | xargs -0 stat | grep File | sort -k2 -n
#find /home/sajidaustria/C0D1NG/file-stats/ -type d  -print0 | xargs -0 stat

find /home/sajidaustria/fs1 -print0 | xargs -0 stat > /home/sajidaustria/fs1-files-statistics.txt
tail -f /home/sajidaustria/fs1-files-statistics.txt

find /home/sajidaustria/fs2 -print0 | xargs -0 stat > /home/sajidaustria/fs2-files-statistics.txt
tail -f  /home/sajidaustria/fs2-files-statistics.txt

find /home/sajidaustria/fs3 -print0 | xargs -0 stat > /home/sajidaustria/fs3-files-statistics.txt
tail -f  /home/sajidaustria/fs3-files-statistics.txt

find /home/sajidaustria/fs4 -print0 | xargs -0 stat > /home/sajidaustria/fs4-files-statistics.txt
tail -f  /home/sajidaustria/fs4-files-statistics.txt

find /home/sajidaustria/fs5 -print0 | xargs -0 stat > /home/sajidaustria/fs5-files-statistics.txt
tail -f  /home/sajidaustria/fs5-files-statistics.txt

find /home/sajidaustria/fs6 -print0 | xargs -0 stat > /home/sajidaustria/fs6-files-statistics.txt
tail -f  /home/sajidaustria/fs6-files-statistics.txt

find /home/sajidaustria/fs7 -print0 | xargs -0 stat > /home/sajidaustria/fs7-files-statistics.txt
tail -f  /home/sajidaustria/fs7-files-statistics.txt

find /home/sajidaustria/fs8 -print0 | xargs -0 stat > /home/sajidaustria/fs8-files-statistics.txt
tail -f  /home/sajidaustria/fs8-files-statistics.txt

find /home/sajidaustria/fs10 -print0 | xargs -0 stat > /home/sajidaustria/fs10-files-statistics.txt
tail -f  /home/sajidaustria/fs10-files-statistics.txt

find /home/sajidaustria/fs11 -print0 | xargs -0 stat > /home/sajidaustria/fs11-files-statistics.txt
tail -f  /home/sajidaustria/fs11-files-statistics.txt

## rows into columns
stat /home/sajidaustria/C0D1NG/file-stats/*


## run script file-stats.py for every file
##for instance :::..
/C0D1NG/file-stats-dev$  python3 file-stats.py  fs4-files-statistics.txt