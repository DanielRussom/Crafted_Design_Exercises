Outside-In TDD
--------------

### Requirements

Create a Twitter engine class that provides the following:

* Accept tweets from users (post)
* Return tweets by a specific user in reverse-chronological order
* Users can follow other users
* Wall: return all tweets from users being followed
	* User's own tweets are also returned as part of her wall. 

### Implementation details

* Create a TwitterEngine class to handle all requests defined above
* Posts contain the Twitter ID of the user posting the tweet
* If a Twitter ID does not exist, it should be treated as a new twitter ID and be accepted. (user is created)
* Tweet must be less than 140 chars to be accepted. If more, an exception should be thrown

### Testing

* Failing acceptance tests are provided. 
* Test-Drive the TwitterEngine, mocking possible collaborators
* Once the TwitterEngine is unit tested, Test-Drive the each collaborator
* Once all collaborators are tested and implemented, one of the acceptance tests should go green.
* Repeat, not focusing on the other failing acceptance test. 

### TIPS

* You many need a TweetValidator for the validation
* You may need a in-memory repository to store user and tweets. Should you have in interface for it?
* Acceptance Tests
	1. After posting a few tweets, you should get the tweets from a specific user in reverse-chronological order
		* It's easier to get a reverse-chronological order sorting by ID than date. 
			* IDs should be created sequentially. Don't worry about it being thread-safe for this exercise.
	2. Different users posts tweets. A user follows a couple of other users. User asks for tweets contained in her "wall"
