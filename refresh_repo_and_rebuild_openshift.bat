git remote add openshift -f ssh://589c7f460c1e669904000020@reminder-rentmap.rhcloud.com/~/git/reminder.git/
git merge openshift/master -s recursive -X ours
git push openshift HEAD
git remote rm openshift