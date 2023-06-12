# TextTransformers
![status](https://github.com/lucapl/TextTransformer/actions/workflows/build_and_test.yml/badge.svg)

![](https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExYzg1ZDkxZGI5NzNiOTY2YjMzNGI0YjI3NDQwNDFiZjkxZTFlZWFmNyZlcD12MV9pbnRlcm5hbF9naWZzX2dpZklkJmN0PWc/7c0bE2bfJrfos/giphy.gif)

## Documentation
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://lucapl.github.io/TextTransformer/javadoc/)

[Classes diagram](https://github.com/lucapl/TextTransformer/wiki/Project-Structure)

[REST communication](https://github.com/lucapl/TextTransformer/wiki/REST-message-sequence)


## Description
For people working with text data, our Text Transformer application will allow you to transform text data (e.g. change case, eliminate duplicates, etc.). The application will be available via GUI as well as remote API, thanks to which it will be possible to integrate it with existing tools.

## GUI
The app offers a graphical user interface where a text can be input 
and transformations can be specified. It works in the same manner as the REST API.

![gui](https://i.imgur.com/cUNjSOZ.png)

The GUI is also capable of opening and saving simple text files.


## Input form
The REST api takes JSON as input in the form of:
```
{
"text": "INPUT",
"transforms": ["TRANSFORM1","TRANSFORM2","TRANSFORM3",...]
}
```
The GET requests should be sent to http://localhost:8080/api/convert
## Possible transformations:
- "up","upper" -> uppercase,
- "low", "lower" -> lowercase,
- "cap", "capital" -> capitalise,
- "rev", "reverser" -> reverse,
- "rev_no_case", "reverse_preserve_case" -> reverse, but preserve the cases of letters at each index,
- "ints", "integers" -> convert integers into text,
- "reals" -> convert fractions into text,
- "acr", "acronymise" -> convert selected words into acronyms,
- "unwind" -> convert selected acronyms into full words,
- "unrepeat" -> remove repeated words in vicinity,
- "ltx", "latex" -> convert latex characters,
