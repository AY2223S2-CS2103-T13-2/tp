---
layout: page
title: User Guide
toc: true
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
***RIZZ***ipe is a **command-based recipe database** that was designed with **versatile tagging** and **searching**
features in mind so you can always find the recipe you need! Make use of ***RIZZ***ipe's many features to achieve your
**culinary rizz**.

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  i.e. in `add NAME`, `NAME` is a parameter which can be used as `add Grilled Salmon`.

* Parameters in square brackets `[]` refer to optional parameters that can be excluded.
  i.e. `n/RECIPE_NAME [d/RECIPE_DURATION]` can be used as `n/Cheese Sandwich d/10 minutes` or `n/Cheese Sandwich`.

* Parameters with `...` behind them can be used multiple times, including 0.
  i.e. `[t/TAGS]` can be used as ` ` (zero times), `t/HALAL`, `t/HALAL t/BREAKFAST` etc.

* Parameters can be in any order
  i.e. if a command specifies `n/RECIPE_NAME d/RECIPE_DURATION`, `d/RECIPE_DURATION n/RECIPE_NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.



</div>

### Adding a recipe: `add`

Come up with a new innovative recipe and want to store it for future reference,
and want to classify it by ingredients? Simply run the `add` command, and follow the prompts!

Format:
`add n/RECIPE_NAME [d/RECIPE_DURATION] [p/RECIPE_PORTION] [t/RECIPE_TAGS]...  [i/-n INGREDIENT_NAME [-a INGREDIENT_AMOUNT] [-e ESTIMATED AMOUNT] [-cn COMMON NAME] [-r REMARKS]... [-s SUBSTITUTION]...]... [s/RECIPE_STEPS]...`

> :bulb: Tip: A recipe can have any number of steps, tags and ingredients (including 0)!
>
> :bulb: Tip: It is only compulsory to include the **recipe name** when you first add the recipe into recipe book!
>
> :bulb: Tip: When adding an ingredient, it is only compulsory to include the **ingredient name**. However, you can also add in additional details such as amount (**RECCOMENDED**) and substitutions etc.!
>
> Although optional, we do however suggest adding in the other fields if possible for your own future reference.
>
> If you have multiple inputs for tags, steps or ingredients, treat each input as a separate field (i.e s/Step1 s/step2).
>
> Each field does not have to be input in order (/p can come before /d etc.).
>
> Likewise, ingredient fields do not have to be input in order (-a can come before -n etc.).
>
> However, for steps, please input the steps in the order that they are intended to be performed.

**Example(s) of usage**:
* `add n/Honey Chicken Rice`
* `add n/Chicken Noodles d/20 minutes p/1-2 people i/-n chicken thigh -a 300g i/-n noodles i/-n soy sauce -a 2 tablespoons -s salt`
* `add n/Peanut Butter Sandwich t/breakfast s/Prepare bread and spread s/Using a knife, spread 2-3 scoops of peanut
  butter s/Serve`


### Listing all recipes: `list`

Ever forget how many recipes you have in your storage? Want to view 'em all? Or
simply want to pick a recipe at random? Just run the `list` command.

Format:
`list`

> Lists all recipes that are in the storage, in the chronological order they were added.
>
> Depending on the size of the window, the user may add multiple columns to display more recipes.
>
> :bulb: Tip: The `list` command can be used to reset the most recently searched list to the full recipe list after performing a `find` command!
>
> In order to zoom in on a particular recipe in the list, user can double-click on the corresponding recipe


### Finding a recipe by name: `find`

Have a certain recipe at the back of your mind that you want to refer to?
Or want to look up all recipes associated with a specific tag?

`find` helps to save your time scrolling through your whole list of recipes by displaying
only those that match any of your specified keywords straight away.

Format:
`find [PROPERTY] KEYWORD [ADDITIONAL KEYWORDS]...`
* :bulb: Tip: Supported properties: `name`, `tag`

i.e. `find name KEYWORD [KEYWORDS]...`, `find tag KEYWORD [KEYWORDS]...`

> Adding a property behind `find` is optional, and if no property is specified, `find` defaults to filtering by `name`.
>
> All keyword queries are case-insensitive. e.g. `chicken` will match `Chicken`
>
> Recipes matching at least one keyword will be returned, e.g. searching `sandwich fries` will match recipes named `cheese fries` and `ham sandwich`
>
> Recipes are listed in the chronological order that they were added.
>
> The order of the keywords does not matter. e.g. ham sandwich will match sandwich ham
>
> Only full words will be matched. e.g. chick will **not** match chicken

Example of usage:
* `find cheese` returns all recipes with the keyword `cheese` in their recipe name
* `find name prawn tofu` returns all recipes with the keyword `prawn` and/or `tofu` in their names
* `find tag western` returns all recipes with the tag `western`


### Deleting a recipe: `delete`

No longer like a certain recipe? Simply delete it from the database by its index!

Format:
`delete INDEX`

> Deletes the dish at the specified `INDEX` of the latest list that was displayed.
>
> The index **must be a positive integer** 1, 2, 3, …

Example of usage:
* `list` followed by `delete 2` deletes the 2nd item stored in the recipe book.
*  `find chicken` followed by `delete 1` will delete the 1st recipe in the displayed results of the find command.


### Searching for substitutions for an ingredient: `sub`

Short of a particular condiment or ingredient to complete your favourite recipe? Have no fear, for the `sub` command
provides you with a hassle-free way to solve your problem by suggesting some common substitutions so that you can complete your
dish with an alternative ingredient!

Format:
`sub INGREDIENT_NAME`

> `sub` searches across your stored recipes and within a preloaded suggested substitutions list to provide you with the most accurate and extensive list of substitutions!
>
> :bulb: Tip: Adding a substitution together with an ingredient is recommended since it will boost the number of substitutions listed when that ingredient is queried in the future!
>
> The search is case-insensitive, i.e. `sub salt` will return all stored substitutions for `Salt` and `salt` across recipes.
>
> Only full words will be matched i.e. `sub chick` will **not** return stored substitutes for `chicken`
>
> For inputs with multiple words, it will only match stored ingredients with the same full multi-word input (case-insensitive) i.e. `sub golden syrup` does not return substitutes for `syrup`
>
> The returned list of ingredients returned will not contain any duplicates.

Example of usage:
* `sub chicken` returns a list of suggested substitutions for the ingredient `chicken`

### Clearing the recipe book: `clear`

Want to change things up and start a new recipe book afresh? Simply run the `clear` command to wipe the memory
and start afresh!

Format:
`clear`


### Asking for assistance: `help`

Unsure or unable to remember our list of commands and how to format your inputs? Fret not, as simply inputting
`help` will save you from your woes.

Format:
`help`

> :bulb: Tip: `help` links to this user guide, which is a quick way to refresh your memory on any command that might have slipped your mind!


### Exiting the program: `exit`

Exits the program and closes the window.

Format:
`exit`


## Managing the Data

### Saving the data

Recipe data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Recipe data are saved as a JSON file `[JAR file location]/data/recipebook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ***RIZZ***ipe will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous recipe home folder.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                                                                                                                                       |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/RECIPE_NAME [d/RECIPE_DURATION] [p/RECIPE_PORTION] [t/RECIPE_TAGS]...  [i/-n INGREDIENT_NAME [-a INGREDIENT_AMOUNT] [-e {ESTIMATED AMOUNT}] [-cn {COMMON NAME}] [-r {REMARKS}]... [-s {SUBSTITUTION}]...]... [s/RECIPE_STEPS]...` <br/> e.g., `add n/Chicken Noodles d/20 minutes p/1-2 people i/-n chicken thigh -a 300g i/-n noodles i/-n soy sauce -a 2 tablespoons -s salt` |                                                                                                                                                             |            |                                      |
| **List**   | `list`                                                                                                                                                                                                                                                                                                                                                                                 |                                                                                                                                                                                                                                                                                                                             |            |                                      |
| **Find**   | `find [PROPERTY] KEYWORD [ADDITIONAL KEYWORDS]...    ` for properties: `name`, `tag` <br/> e.g., `find cheese rice`, `find name popcorn`, `find tag western`                                                                                                                                                                                                                           |
| **Delete** | `delete INDEX`<br/> e.g., `delete 2`                                                                                                                                                                                                                                                                                                                                                   |
| **Sub**    | `sub INGREDIENT_NAME`<br/> e.g. `sub salt`                                                                                                                                                                                                                                                                                                                                             |
| **Clear**  | `clear`                                                                                                                                                                                                                                                                                                                                                                                |                                                                                                                                                                          |            |                                      |
| **Help**   | `help`                                                                                                                                                                                                                                                                                                                                                                                 |
| **Exit**   | `exit`                                                                                                                                                                                                                                                                                                                                                                                 |   


