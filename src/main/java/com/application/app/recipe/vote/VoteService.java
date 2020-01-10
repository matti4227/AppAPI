package com.application.app.recipe.vote;

import com.application.app.applicationUser.ApplicationUser;
import com.application.app.recipe.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService implements VoteServiceInterface {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteRepositoryInterface voteRepositoryInterface;

    @Override
    public Vote createVote(Recipe recipe, ApplicationUser user, int score) {
        Vote vote = voteRepository.createVote(recipe, user, score);
        return voteRepositoryInterface.save(vote);
    }

    @Override
    public List<Vote> getVotesByRecipe(Recipe recipe) {
        return voteRepositoryInterface.findAllByRecipe(recipe);
    }
}
