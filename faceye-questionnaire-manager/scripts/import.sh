#mongo_path=/data/tools/mongo/mongodb-linux-x86_64-3.0.7
#localhost
#mongo_path=/tools/mongodb/mongodb-osx-x86_64-3.0.1
#produtc
mongo_path=/app/mongo/mongodb-linux-x86_64-3.0.1
#export_path=/data/deploy/csj
#export_path=/work/Work/FeatureWorkSpace/feature/faceye-questionnaire/faceye-questionnaire-manager/data/csj
export_path=/tmp/csj
db=cms
$mongo_path/bin/mongoimport -d $db -c questionnaire_answer $export_path/questionnaire_answer
$mongo_path/bin/mongoimport -d $db -c questionnaire_answer_record  $export_path/questionnaire_answer_record
$mongo_path/bin/mongoimport -d $db -c questionnaire_answerStat  $export_path/questionnaire_answerStat
$mongo_path/bin/mongoimport -d $db -c questionnaire_question  $export_path/questionnaire_question
$mongo_path/bin/mongoimport -d $db -c questionnaire_questionnaire  $export_path/questionnaire_questionnaire
$mongo_path/bin/mongoimport -d $db -c questionnaire_questionnaire_type $export_path/questionnaire_questionnaire_type
$mongo_path/bin/mongoimport -d $db -c global_upload_file $export_path/global_upload_file
