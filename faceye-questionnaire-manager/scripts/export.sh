#120.25.2.75
#mongo_path=/data/tools/mongo/mongodb-linux-x86_64-3.0.7
#localhost
mongo_path=/tools/mongodb/mongodb-osx-x86_64-3.0.1
#product
#mongo_path=/app/mongo/mongodb-linux-x86_64-3.0.1
#120.25.2.75
#export_path=/data/deploy/csj
#localhost
export_path=/work/csj
$mongo_path/bin/mongoexport -d search -c questionnaire_answer -o $export_path/questionnaire_answer
$mongo_path/bin/mongoexport -d search -c questionnaire_answer_record -o $export_path/questionnaire_answer_record
$mongo_path/bin/mongoexport -d search -c questionnaire_answerStat -o $export_path/questionnaire_answerStat
$mongo_path/bin/mongoexport -d search -c questionnaire_question -o $export_path/questionnaire_question
$mongo_path/bin/mongoexport -d search -c questionnaire_questionnaire -o $export_path/questionnaire_questionnaire
$mongo_path/bin/mongoexport -d search -c questionnaire_questionnaire_type -o $export_path/questionnaire_questionnaire_type
$mongo_path/bin/mongoexport -d search -c global_upload_file -o $export_path/global_upload_file

