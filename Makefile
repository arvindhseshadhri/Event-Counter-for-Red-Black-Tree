JFLAGS = -g
JC = javac -cp . EventCounterRedBlackTree.java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = EventCounterRedBlackTree.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
