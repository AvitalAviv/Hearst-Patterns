# Hearst Patterns
Written in Java, using Hearst patterns and Object Oriented design principles as part of OOP course at Bar Ilan University. 

## Abstract
**Hypernymy** (also called IS-A relation) is a semantic relation between two noun phrases, **hypernym** and **hyponym**, such that the hyponym is a subtype of the hypernym. For example, cat and dog are hyponym of animal, which is a hypernym.

A well-established approach to do so is using lexico-syntactic patterns, often called **Hearst patterns**.

So, out goal in this exercise is to find all the Hearst patterns from a given corpus of data using regular expressions, and then by a given **hypernym** (an input as second argument), find all his relations.

## Parts and explanations

Hearst patterns contains five main patterns, and according to that, there is five classes, each one represents one of the patterns.

**Part 1** - constructing a database of hypernym relations. Going over a corpus that contains lots of files to read and to organize into a Map. All relations saved into another file.

**Part 2** - Hypernym discovery - by a given hypernym as an argument, get an output of all his relations in the corpus. 

## How to run it
(need to have ant compile code).

In order to run the exercise, attached a small corpus. 

At **part 1**, need to put as first argument the path of this corpus, and the first argument is a path to output from the analyser.

At **part 2**, the only argument is the hypernym as described.

