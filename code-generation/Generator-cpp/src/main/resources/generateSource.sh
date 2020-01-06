mkdir -p ../java/org/se/lab/
cpp $1 templates/Main.java | egrep -v "^#.*" > ../java/org/se/lab/Main.java
