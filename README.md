### basic-poker-game
This project is an API or software application (service) designed and implemented to run on the backend (server side) for an online pocker game which could be played between multiple players and on multiple decks scenarios.

### Technical view

This is a java server project using Java 1.8 with Spring Boot 2.1.1.RELEASE and Maven for dependency management.
For Test Unit, I used Spring and JUnit.
The database is a h2 in memory database maven.

### Game Controller

## List decks (/decks)
Show the list of all game decks

## List players (/players)
Show the list of all players

## Show a player (/players/{playerid})
Show a particular player. playerid is the unique ID for the player

## Show player cards (/players/{playerid}/cards)
Get the list of cards for a player. playerid is the unique ID for the player

## List games (/games)
Show the list of all games

## Add new game (/games/addgame)
Add a new game

## Delete a game (/games/{gameid}/delete)
Delete a game. gameid is the unique ID for the game

## Add a deck (/games/adddeck/{gameid})
Add a new deck to a game. gameid is the unique ID for the game

## Add a player (/games/addplayer/{gameid})
Add a new player to a game. gameid is the unique ID for the game

## Remove a player (/games/{gameid}/removeplayer/{playerid})
Remove a player from a game. gameid is the unique ID for the game.
playerid is the unique ID for the player

## Deal card to each player (/games/{gameid}/deal)
Deal cards to each player in a game from the game deck.
gameid is the unique ID for the game

## Return count of undealt cards (/games/{gameid}/undealt)
Get the count of how many cards per suit are left undealt in the game deck

## Return count of each card remaining in the game ((/games/{gameid}/remaining)
Get the count of each card remaining in the game deck sorted by suit and face value

## List player with their total values ((/games/{gameid}/players)
Get the list of players in a game along with the total added value of all the cards each player holds
