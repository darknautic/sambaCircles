#! /bin/bash

echo -e "\n Notify duplicated lines"
fs1=`cat fs1-files-statistics.txt | grep File  | sort -u | wc -l`
fs1Uniq=`cat fs1-files-statistics.txt | grep File  | wc -l`

fs2=`cat fs2-files-statistics.txt | grep File  | sort -u | wc -l`
fs2Uniq=`cat fs2-files-statistics.txt | grep File  | wc -l`

fs3=`cat fs3-files-statistics.txt | grep File  | sort -u | wc -l`
fs3Uniq=`cat fs3-files-statistics.txt | grep File  | wc -l`

fs4=`cat fs4-files-statistics.txt | grep File  | sort -u | wc -l`
fs4Uniq=`cat fs4-files-statistics.txt | grep File  | wc -l`

fs5=`cat fs5-files-statistics.txt | grep File  | sort -u | wc -l`
fs5Uniq=`cat fs5-files-statistics.txt | grep File  | wc -l`

fs6=`cat fs6-files-statistics.txt | grep File  | sort -u | wc -l`
fs6Uniq=`cat fs6-files-statistics.txt | grep File  | wc -l`

fs7=`cat fs7-files-statistics.txt | grep File  | sort -u | wc -l`
fs7Uniq=`cat fs7-files-statistics.txt | grep File  | wc -l`

fs8=`cat fs8-files-statistics.txt | grep File  | sort -u | wc -l`
fs8Uniq=`cat fs8-files-statistics.txt | grep File  | wc -l`

fs10=`cat fs10-files-statistics.txt | grep File  | sort -u | wc -l`
fs10Uniq=`cat fs10-files-statistics.txt | grep File  | wc -l`

fs11=`cat fs11-files-statistics.txt | grep File  | sort -u | wc -l`
fs11Uniq=`cat fs11-files-statistics.txt | grep File  | wc -l`

echo -e "\nfs1 :" $fs1  $fs1Uniq
echo -e "\nfs2 :" $fs2  $fs2Uniq
echo -e "\nfs3 :" $fs3  $fs3Uniq
echo -e "\nfs4 :" $fs4  $fs4Uniq
echo -e "\nfs5 :" $fs5  $fs5Uniq
echo -e "\nfs6 :" $fs6  $fs6Uniq
echo -e "\nfs7 :" $fs7  $fs7Uniq
echo -e "\nfs8 :" $fs8  $fs8Uniq
echo -e "\nfs9 :" $fs9  $fs9Uniq
echo -e "\nfs10 :" $fs10  $fs10Uniq
echo -e "\nfs11 :" $fs11  $fs11Uniq



