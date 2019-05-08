# Created by James McConnel
# January 29, 2009
# Purpose: a makefile for building RecipeJar from the command line.

SHELL = /bin/bash

vpath %.java src/recipejar
vpath %.class build/classes

JAVAC= javac -g -d build -classpath build
RESOURCES = src/about.txt src/default.css src/filetree.txt src/index.css \
            src/index.template src/macros.txt src/recipejar.gif \
            src/recipe.template src/units.txt src/welcome.html

dist/RecipeJar.jar: Main.class cmdLineTools/manifest.txt $(RESOURCES)
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
#:taxpayer1 \
#taxpayer2

   data/RecipeFile.class \
   data/IndexFile.class \
:data/AbstractXHTMLBasedFile.class

   data/RecipeFile.class \
:data/Prefs.class \
data/Ingredient.class \
data/RecipeTemplate.class \
Util.class

   data/Ingredient.class \
:data/Units.class

   data/Units.class \
:data/UnitFile.class

   data/UnitFile.class \
:data/AbstractCharDelineatedFile.class \
data/Unit.class

   data/Prefs.class \
:Util.class \
data/LAFType.class \
data/UIMode.class

   data/AbstractXHTMLBasedFile.class \
:data/Element.class

   data/IndexFile.class \
:data/RecipeFile.class \
data/IndexTemplate.class \
data/Anchor.class \
Util.class

   ui/AlphaTab.class \
:data/IndexFile.class \
data/Index.class

   recipe/editor/IList/UnitCellEditor.class \
   recipe/editor/IList/IngredientList.class \
   recipe/editor/IList/IListTable.class \
:recipe/editor/IList/Ingredient.class

# Full list of classes
   Main.class \
:OSAdapter.class \
Util.class \
data/AbstractCharDelineatedFile.java \
data/AbstractXHTMLBasedFile.java \
data/Anchor.class \
event/DefaultActionClearingHouse.class \
ui/UserInterface.class \
data/Data.class \
data/Element.class \
data/Index.class \
data/IndexFile.class \
data/IndexStyle.class \
data/IndexTemplate.class \
data/Ingredient.class \
data/IngredientTableModel.class \
data/LAFType.class \
data/Macro.class \
data/MacroFile.class \
data/Macros.class \
data/Prefs.class \
data/Recipe.class \
data/RecipeFile.class \
data/RecipeTemplate.class \
data/StyleSheet.class \
data/UIMode.class \
data/Unit.class \
data/UnitFile.class \
data/Units.class \
data/WelcomeMsg.class \
event/AbstractTextAction.class \
event/ApplicationEvent.class \
event/ApplicationEventListener.class \
event/ApplicationEventSource.class \
event/Event_Type.class \
event/IndexKeyListener.class \
event/MacroTextAction.class \
ui/AlphaTab.class \
ui/AlphaTab.class \
ui/EditorPanel.class \
ui/FilePrefPanel.class \
ui/IListTable.class \
ui/PreferencesDialog.class \
ui/RecipeJarFrame.class \
ui/SearchDialog.class \
ui/TextCellEditor.class \
ui/UnitCellEditor.class \
ui/UnitConverterDialog.class \
ui/rjTextPane.class \

# end File Type dependencies

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
