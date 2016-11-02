## Short description
If you want to organize some event and want to know an actual list of participants it might be very helpful to use this
telegram bot. It allows to create events, record yourself on a current event and view list of participants.

The bot has several commands.

* /new - creates a new event (an old event marked as inactive and now can't be access later)
* /i - attend a current event
* /cant - removes you from a current event
* /event - shows list of participant

## About implementation
* Kotlin lang
* MongoDb
* Spring
* https://github.com/rubenlagus/TelegramBots (telegram bot java library)
* Heroku as hosting

##
Tests (not a lot unfortunately) requires mongodb running on localhost:27017