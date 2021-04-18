@echo off

SET PATH="C:\Program Files\BlueJ\jdk\bin";%PATH
javadoc -d userdoc -author -version *.java
javadoc -d progdoc -author -version -private -linksource *.java

pause