## Short description
If you want to organize some event and want to know an actual list of participants it might be very helpful to use this
telegram bot. It allows to create events, record yourself (or your friend who's not a telegram user) on a current event and view list of participants.

## How to use
You should add bot @Event_Organizer_Bot (not @EventOrganizerBot) to your chat. It would receive only command-messages (all the other wouldn't be available for it). 

The bot has several commands. Arguments in braces are optional.

* /new - creates a new event (an old event marked as inactive and now can't be access later)
* /i [name of a participant who's not in telegram]- attend a current event
* /cant [index of participant (should be invited by you)]- removes you from a current event
* /event - shows list of participant

## About implementation
* Kotlin lang
* MongoDb
* Spring
* https://github.com/rubenlagus/TelegramBots (telegram bot java library)
* Heroku as hosting

## Restrictions
Currently bot deployed on free heroku, so it could work slow. But it's easy to deploy the same bot for yourself, just ask me how.

## Comments, suggestions and contributions
Please, comment, suggest and contribute :) https://github.com/achugr/event-organizer-bot/issues

## P.S.
Tests (not a lot unfortunately) requires mongodb running on localhost:27017
