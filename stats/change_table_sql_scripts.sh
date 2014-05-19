#! /bin/bash
echo -e "\nchanging table in sql scripts"

sed 's/filestats/filestats2/g' fs2-files-statistics_script.sql > fs2-files-statistics_script.sql_aux ; mv fs2-files-statistics_script.sql_aux fs2-files-statistics_script.sql
echo -e "2 done"
sed 's/filestats/filestats3/g' fs3-files-statistics_script.sql > fs3-files-statistics_script.sql_aux ; mv fs3-files-statistics_script.sql_aux fs3-files-statistics_script.sql
echo -e "3 done"
sed 's/filestats/filestats4/g' fs4-files-statistics_script.sql > fs4-files-statistics_script.sql_aux ; mv fs4-files-statistics_script.sql_aux fs4-files-statistics_script.sql
echo -e "4 done"
sed 's/filestats/filestats5/g' fs5-files-statistics_script.sql > fs5-files-statistics_script.sql_aux ; mv fs5-files-statistics_script.sql_aux fs5-files-statistics_script.sql
echo -e "5 done"
sed 's/filestats/filestats6/g' fs6-files-statistics_script.sql > fs6-files-statistics_script.sql_aux ; mv fs6-files-statistics_script.sql_aux fs6-files-statistics_script.sql
echo -e "6 done"
sed 's/filestats/filestats7/g' fs7-files-statistics_script.sql > fs7-files-statistics_script.sql_aux ; mv fs7-files-statistics_script.sql_aux fs7-files-statistics_script.sql
echo -e "7 done"
sed 's/filestats/filestats8/g' fs8-files-statistics_script.sql > fs8-files-statistics_script.sql_aux ; mv fs8-files-statistics_script.sql_aux fs8-files-statistics_script.sql
echo -e "8 done"
sed 's/filestats/filestats10/g' fs10-files-statistics_script.sql > fs10-files-statistics_script.sql_aux ; mv fs10-files-statistics_script.sql_aux fs10-files-statistics_script.sql
echo -e "10 done"
sed 's/filestats/filestats11/g' fs11-files-statistics_script.sql > fs11-files-statistics_script.sql_aux ; mv fs11-files-statistics_script.sql_aux fs11-files-statistics_script.sql
echo -e "11 done"



sed 's/insert  into filestats values /insert ignore  into filestats5_new (filepath,size_bytes,filetype,accessDate,modifyDate,changeDate) values /g' fs5-files-statistics_script.sql  > fs5-files-statistics_script.sql_aux ; mv  fs5-files-statistics_script.sql_aux fs5-files-statistics_script.sql