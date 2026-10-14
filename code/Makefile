compil :
	javacc -OUTPUT_DIRECTORY:./src/fr/insarouen/iti/prog/sgbd/analyseur ./src/fr/insarouen/iti/prog/sgbd/analyseur/AnalyseurSyntaxique.jj
	javac -classpath ./classes -sourcepath ./src -d ./classes $$(find ./src -name "*.java" ! -path "*/analyseur/*")

test : 
	javac -classpath ./classes:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-all.jar -sourcepath ./srctest -d ./classestest $$(find ./srctest -name "*.java")

run :
	java -classpath ./classes:./classestest:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-all.jar org.junit.runner.JUnitCore fr.insarouen.iti.prog.sgbd.AllTests
clean :
	rm -rf ./classes
	rm -rf ./classestest
	rm -rf ./src/fr/insarouen/iti/prog/sgbd/analyseur/*.java
	rm -rf ./doc

doc:
	javadoc -d doc -sourcepath src -subpackages fr.insarouen.iti.prog