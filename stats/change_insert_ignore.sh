#! /bin/bash
echo -e "\nchanging insert to insert ingnore"



sed 's/insert/INSERT IGNORE/g' fs1-files-statistics_script.sql > fs1-files-statistics_script.sql_aux ; mv fs1-files-statistics_script.sql_aux fs1-files-statistics_script.sql
echo -e "1 done"
sed 's/insert/INSERT IGNORE/g' fs2-files-statistics_script.sql > fs2-files-statistics_script.sql_aux ; mv fs2-files-statistics_script.sql_aux fs2-files-statistics_script.sql
echo -e "2 done"
sed 's/insert/INSERT IGNORE/g' fs3-files-statistics_script.sql > fs3-files-statistics_script.sql_aux ; mv fs3-files-statistics_script.sql_aux fs3-files-statistics_script.sql
echo -e "3 done"
sed 's/insert/INSERT IGNORE/g' fs4-files-statistics_script.sql > fs4-files-statistics_script.sql_aux ; mv fs4-files-statistics_script.sql_aux fs4-files-statistics_script.sql
echo -e "4 done"
sed 's/insert/INSERT IGNORE/g' fs5-files-statistics_script.sql > fs5-files-statistics_script.sql_aux ; mv fs5-files-statistics_script.sql_aux fs5-files-statistics_script.sql
echo -e "5 done"
sed 's/insert/INSERT IGNORE/g' fs6-files-statistics_script.sql > fs6-files-statistics_script.sql_aux ; mv fs6-files-statistics_script.sql_aux fs6-files-statistics_script.sql
echo -e "6 done"
sed 's/insert/INSERT IGNORE/g' fs7-files-statistics_script.sql > fs7-files-statistics_script.sql_aux ; mv fs7-files-statistics_script.sql_aux fs7-files-statistics_script.sql
echo -e "7 done"
sed 's/insert/INSERT IGNORE/g' fs8-files-statistics_script.sql > fs8-files-statistics_script.sql_aux ; mv fs8-files-statistics_script.sql_aux fs8-files-statistics_script.sql
echo -e "8 done"
sed 's/insert/INSERT IGNORE/g' fs10-files-statistics_script.sql > fs10-files-statistics_script.sql_aux ; mv fs10-files-statistics_script.sql_aux fs10-files-statistics_script.sql
echo -e "10 done"
sed 's/insert/INSERT IGNORE/g' fs11-files-statistics_script.sql > fs11-files-statistics_script.sql_aux ; mv fs11-files-statistics_script.sql_aux fs11-files-statistics_script.sql
echo -e "11 done"

