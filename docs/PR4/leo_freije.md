**Note that all tests are in one file, so I provided the command I ran and the result as screenshots for each bug.**

**Bug \#1:** Issue 85 “\[LF-03\] Fix rejecting hazard description below minimum length”  
Issue: **[\#85](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/85)**  
PR: \[link\]

.\\gradlew.bat :shared:jvmTest \--tests "edu.sdsu.cs250.team5.road\_watch.ReportValidatorTest.rejectsLessThanMinimumDescription"

Before:  
\!\[before\](/docs/PR4/screenshots/leo\_freije\_issue85\_before.png)

After:  
\!\[after\](/docs/PR4/screenshots/leo\_freije\_issue85\_after.png)

Code Bug: ReportValidator.kt did not have a check for a minimum length.

**Bug \#2:** Issue 86 “\[LF-06\] Fix rejecting hazard description above maximum length”  
Issue: **[\#86](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/86)**   
PR: \[link\]

.\\gradlew.bat :shared:jvmTest \--tests "edu.sdsu.cs250.team5.road\_watch.ReportValidatorTest.testMaximumDescriptionLength"

Before:  
\!\[before\](/docs/PR4/screenshots/leo\_freije\_issue86\_before.png)

After:  
\!\[after\](/docs/PR4/screenshots/leo\_freije\_issue86\_after.png)

Code Bug: There was originally an equal sign in the code that checked the maximum report length.