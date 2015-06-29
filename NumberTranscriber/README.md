# Number trancriptor

Project to tranform a given number in digital format to a literal with the number trancription.

The task can be solved in one class, but instead of this a Factory patter has been used to allow the addition of transcriptors in new languages.

A TDD approach has been followed to perform the implementation.

Example of use:

Transcriber transcriber = TranscriberSelector.createTranscriber(AvailableLanguages.ENGLISH);
transcriber.numberTranscription(<long_number>);