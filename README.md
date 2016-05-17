## Short description
If you want to organize some event and want to know an actual list of participants it might be very helpful to use this
telegram bot. It allows to create events, record yourself on a current event and view list of participants.

The bot has several commands. Now the commands list designed for a football game.

* /new - creates a new game (an old game marked as inactive and now can't be access later)
* /i - attend a current game
* /cant - removes you from a current game
* /game - shows list of participant

## About implementation
* Kotlin lang
* MongoDb
* Spring
* https://github.com/rubenlagus/TelegramBots (telegram bot java library)
* Heroku as hosting

##
Tests (only 2 now :( ) requires mongod running on localhost:27017