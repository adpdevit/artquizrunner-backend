# artquizrunner-backend
Backend monitoring quiz game -- using JWT for stateless game state gesture

## Context

Please refer to https://github.com/Valefrg/art-quiz-runner to know more on the context of the project

## Run

Build the project with maven and run the output jar or execute the latest jar build (/exec folder)

password for admin operations : dummypass

## Short

This project has been developed by @adpdevit for a little game project led by @valefrg. The main purpose was to provide a server that maintains a quizz game state in a
stateless mode (less complexity). Json Web Tokens were used to provide a minimum security to protect the integrity of the score board (even if some cheat use cases can't
be prevented without maintaining a state).

## Tech

This is a spring boot server linked to a free hosted mongodb instance
Nothing fancy used here :.)
