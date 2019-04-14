# Created by James McConnel
# January 29, 2009
# Purpose: a makefile for building RecipeJar from the command line.

SHELL = /bin/bash

vpath %.java src/recipejar
vpath %.class build/classes

JAVAC= javac -g -d build -classpath build
RESOURCES = src/about.txt src/default.css src/filetree.txt src/index.css \
            src/index.html src/macros.txt src/recipejar.gif src/state.ini \
            src/template.html src/units.txt src/welcome.html

dist/RecipeJar.jar: RecipeJarFrame.class cmdLineTools/manifest.txt $(RESOURCES)
	jar -cfm dist/RecipeJar.jar cmdLineTools/manifest.txt @cmdLineTools/jarlist

RecipeJar.app: dist/RecipeJar.jar
	mkdir -pv RecipeJar.app/Contents/Resources/Java
	mkdir -v RecipeJar.app/Contents/MacOS
	cp -v /System/Library/Frameworks/JavaVM.framework/Versions/Current/Resources/MacOS/JavaApplicationStub RecipeJar.app/Contents/MacOS
	cp -v cmdLineTools/Info.plist RecipeJar.app/Contents
	cp -v cmdLineTools/PkgInfo RecipeJar.app/Contents/MacOS
	cp -v cmdLineTools/RecipeJar.icns RecipeJar.app/Contents/Resources
	cp -v dist/RecipeJar.jar RecipeJar.app/Contents/Resources/Java
	SetFile -a B RecipeJar.app

############################
# Dependency Definitions
############################
# syntax:
#   dependent1 \
#   dependent2 \
#:dependency

   data/RecipeFile.class \
   data/IndexFile.class \
:data/AbstractXHTMLBasedFile.class

   data/RecipeFile.class \
:data/Prefs.class

   data/LAFType.class \
   data/Prefs.class \
:Util.class

   data/AbstractXHTMLBasedFile.class \
:data/Element.class

   data/IndexFile.class \
:data/RecipeFile.class

   ui/AlphaTab.class \
:data/IndexFile.class

# end File Type dependencies

   RecipeJarFrame.class \
:ui/AlphaTab.class

   recipe/editor/IList/UnitCellEditor.class \
   recipe/editor/IList/IngredientList.class \
   recipe/editor/IList/IListTable.class \
:recipe/editor/IList/Ingredient.class


#
# implicit rule for Java files
#
%.class: %.java
	$(JAVAC) $<

##########################################
#Other Commands
##########################################

.PHONY: clean
clean:
	if test -d build; then rm -r build; fi;
	mkdir build
	if test -d dist; then rm -r dist; fi;
	mkdir dist
	if test -d RecipeJar.app; then rm -r RecipeJar.app; fi;
