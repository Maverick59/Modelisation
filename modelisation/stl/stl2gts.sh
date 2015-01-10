#!/bin/bash
echo "-- STL2GTS --"
for f in $(find . -iname '*.stl') ; do 
	stl2gts <$f> ../model/$f.gts ;
	echo "<$f>";
done
echo "-- END --"
