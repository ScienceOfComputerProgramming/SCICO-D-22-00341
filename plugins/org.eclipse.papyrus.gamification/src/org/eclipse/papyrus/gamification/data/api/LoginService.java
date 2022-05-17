package org.eclipse.papyrus.gamification.data.api;

import java.util.List;

import org.eclipse.papyrus.gamification.data.api.query.SponsorRequest;
import org.eclipse.papyrus.gamification.data.api.query.UserCreationRequest;
import org.eclipse.papyrus.gamification.data.api.response.GameJson;
import org.eclipse.papyrus.gamification.data.api.response.PlayerListJson;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginService {


	@GET("/gamification/model/game")
	Single<List<GameJson>> getAllGames();

	@GET("/gamification/gengine/state/{gameId}")
	Single<PlayerListJson> getPlayersOfGame(@Path("gameId") String gameId, @Query("size") int sizeOfPage);

	@POST("/gamification/data/game/{gameId}/player/{playerId}")
	Completable createPlayerForGame(@Path("gameId") String gameId,
			@Path("playerId") String playerId,
			@Body UserCreationRequest content);

	@POST("/gamification/exec/game/{gameId}/action/{actionId}")
	Completable invitePlayer(@Path("gameId") String gameId,
			@Path("actionId") String actionId,
			@Body SponsorRequest content);


}
