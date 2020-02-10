## What is MixMatch
MixMatch is a basic, but challenging, memory matching game. The player is the select a puzzle size, then find all the matching pairs of icons.

## Code Documentation
All of the code is documented. Follows a loose model view controller pattern. Any curiosities about implementation could be found where expected in the code.

## Execution
- There is a "mixmatch.jar" file at src level, and the original artifact location is "out/artifacts/mixmatch_jar/mixmatch.jar".
- Alternatively you can run main, which is located in the MixMatch directory.

## Design
- Images are courtesy of an identicon generating script I wrote in Elixir. The names of the identicons are actually the words that were used to generate each unique icon.
- Using icons that are not everyday pictures like airplanes or plus symbols adds some depth to this version of a matching game. It also makes it a bit more challenging. 
- For accessibility purposes, none of the icons are the same shape. So, color is not the only identifier.
- Originally I was going to time the user's performance, but I noticed the bigger puzzles could take some time to complete.
- I decided on a click-counter as a performance metric that shows users how many tiles they clicked to solve the puzzle.

## Testing
I had planned on writing unit tests, but this was mostly an exploration of GUI development with Java swing. Needless to say, most of the app is GUI and was manually tested. 

## Personal reflection
I wanted to reflect here as I did not necessarily enjoy making this game. I had never done much GUI development outside of the web and I must say Java Swing is not a fun way to make a GUI, especially one that looks modern. Perhaps I should re-imagine a similar game through the web.
