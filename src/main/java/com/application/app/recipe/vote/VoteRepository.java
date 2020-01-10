package com.application.app.recipe.vote;

import com.application.app.applicationUser.ApplicationUser;
import com.application.app.recipe.Recipe;
import org.springframework.stereotype.Repository;

@Repository
public class VoteRepository {

    public Vote createVote(Recipe recipe, ApplicationUser user, int score) {

        Vote vote = new Vote();
        vote.setRecipe(recipe);
        vote.setScore(score);
        vote.setUser(user);

        return vote;
    }
}
