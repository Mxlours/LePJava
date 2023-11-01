# Example de makefile pour compiler le squelette de code distribué
# Vous pouvez compléter ce makefile, mais étant donnée la taille du projet, 
# il est FORTEMENT recommandé d'utiliser un IDE!

# Organisation:
#  1) Les sources (*.java) se trouvent dans le répertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) sont générés dans le répertoire bin
#     La hiérarchie des sources (par package) est conservée.
#
#  3) Une librairie gui.jar est distribuée pour l'interface grapique. 
#     Elle se trouve dans le sous-répertoire lib.
#
# Compilation:
#  Options de javac:
#   -d : répertoire dans lequel sont générés les .class compilés
#   -sourcepath : répertoire dans lequel sont cherchés les .java
#   -classpath : répertoire dans lequel sont cherchées les classes compilées (.class et .jar)

all: runTestInvader runTestBall runTestBallSimulator runTestCell runTestCellSimulator

compileTestInvader:
	javac -d bin -classpath lib/gui.jar src/TestInvader.java

runTestInvader: compileTestInvader
	java -classpath bin:lib/gui.jar TestInvader

compileTestBall:
	javac -d bin -classpath lib/gui.jar src/TestBalls.java src/Balls.java

runTestBall: compileTestBall
	java -classpath bin:lib/gui.jar TestBalls

compileTestBallSimulator:
	javac -d bin -classpath lib/gui.jar src/TestBallsSimulator.java src/Balls.java src/BallsSimulator.java

runTestBallSimulator: compileTestBallSimulator
	java -classpath bin:lib/gui.jar TestBallsSimulator

compileTestCell:
	javac -d bin -classpath lib/gui.jar src/TestCell.java src/Cell.java

runTestCell: compileTestCell
	java -classpath bin:lib/gui.jar TestCell

compileTestCellSimulator:
	javac -d bin -classpath lib/gui.jar src/TestCellSimulator.java src/Cell.java src/CellSimulator.java

runTestCellSimulator: compileTestCellSimulator
	java -classpath bin:lib/gui.jar TestCellSimulator

clean:
	rm -rf bin/

