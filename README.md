# autoshuttlelog_Server

## Deploy Instruction
 Download 
 
 [Tomcat(7+)](https://tomcat.apache.org/download-90.cgi)
 
 [Maven(3+)](https://maven.apache.org/download.cgi)
 
 [Eclipse JavaEE IDE](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/oxygen3)
 
 
 ### Develop mode with eclipse
 
 1. Clone Code
 
 2. Go to Eclipse, import - Maven - Existing Maven Project, improt code into workspace.
 
 3. Rightclick on Project folder, Maven - Update Project - OK
 
 4. Set up a tomcat server if you haven't done that yet, please google instructions. Remember to set the timeout to 450+.
 
 5. Right click on the tomcat server, Add and Remove, add shuttle log. 
 
 6. And you are good to Run or debug.
 
  ### Deploy with Maven Tomcat
  
  If you are using windows, remember to set the enviroment variables properly (please google instructions).
  
  Please refer to [This Instruction](https://www.mkyong.com/maven/how-to-deploy-maven-based-war-file-to-tomcat/)
  
  I've done the pom.xml part, you'll need the Maven settings and tomcat user configurations.
  
  Then tart Tomcat (run startup.bash or .sh under bin folder),
  
  Go to code and 

  ```
  mvn tomcat6:deploy
  ```
  
 
 
 
