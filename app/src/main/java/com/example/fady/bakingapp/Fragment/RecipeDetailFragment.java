package com.example.fady.bakingapp.Fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fady.bakingapp.DetailsActivity;
import com.example.fady.bakingapp.Model.Recipe;
import com.example.fady.bakingapp.Model.Step;
import com.example.fady.bakingapp.R;
import com.example.fady.bakingapp.RecipesActivity;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkSource;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends android.app.Fragment {
    SimpleExoPlayerView exoPlayerView;
    Button previous, next;
    TextView shortDesc, longDesc;
    ArrayList<Step> steps;
    String url = null;
    Bundle bundle2;
    int pos;


    private static final DefaultBandwidthMeter
            BANDWIDTH_METER = new DefaultBandwidthMeter();
    RecipeDetailFragment recipeDetailFragment;
    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;
    SimpleExoPlayer player;
    ImageView stepImage;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        exoPlayerView = (SimpleExoPlayerView) root.findViewById(R.id.myExoplayerView);
        stepImage = (ImageView) root.findViewById(R.id.stepImage);
        next = (Button) root.findViewById(R.id.nextStep);
        previous = (Button) root.findViewById(R.id.previousStep);
        shortDesc = (TextView) root.findViewById(R.id.shortDescreption);
        longDesc = (TextView) root.findViewById(R.id.longDescreption);
        Bundle bundle = getArguments();
        bundle2 = new Bundle();
        steps = bundle.getParcelableArrayList("RecipeStepArraylist");
        recipeDetailFragment = new RecipeDetailFragment();
        pos = bundle.getInt("Pos");


        url = steps.get(pos).getVideoURL();
        if (url.equals("")) {
            exoPlayerView.setVisibility(View.GONE);
            stepImage.setVisibility(View.VISIBLE);
            if (!steps.get(pos).getThumbnailURL().equals(""))
                Picasso.with(getActivity()).load(steps.get(pos).getThumbnailURL()).error(R.drawable.cook).placeholder(R.drawable.cook).into(stepImage);
            else
                stepImage.setImageResource(R.drawable.cook);
        } else {
            exoPlayerView.setVisibility(View.VISIBLE);
            stepImage.setVisibility(View.GONE);
        }

        if (Util.SDK_INT > 23) {
            initializePlayer(url);
        }
        shortDesc.setText(steps.get(pos).getShortDescription());
        longDesc.setText(steps.get(pos).getDescription());
        if (pos == 0) {
            previous.setVisibility(View.INVISIBLE);
        } else {
            previous.setVisibility(View.VISIBLE);
        }
        if (pos == steps.size() - 1) {
            next.setVisibility(View.INVISIBLE);
        } else {
            next.setVisibility(View.VISIBLE);
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle2.putParcelableArrayList("RecipeStepArraylist", steps);
                pos++;
                bundle2.putInt("Pos", pos);
                recipeDetailFragment.setArguments(bundle2);
                if (DetailsActivity.istablet == true) {
                    getFragmentManager().beginTransaction().addToBackStack("a").replace(R.id.myframe2, recipeDetailFragment).commit();
                    RecipesActivity.FrNum++;
                } else {
                    getFragmentManager().beginTransaction().addToBackStack("a").replace(R.id.myframe, recipeDetailFragment).commit();
                    RecipesActivity.FrNum++;
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            bundle2.putParcelableArrayList("RecipeStepArraylist", steps);
                                            pos--;
                                            bundle2.putInt("Pos", pos);
                                            recipeDetailFragment.setArguments(bundle2);
                                            if (DetailsActivity.istablet == true) {
                                                getFragmentManager().beginTransaction().addToBackStack("a").replace(R.id.myframe2, recipeDetailFragment).commit();
                                                RecipesActivity.FrNum++;
                                            }else {
                                                getFragmentManager().beginTransaction().addToBackStack("a").replace(R.id.myframe, recipeDetailFragment).commit();
                                                RecipesActivity.FrNum++;

                                            }
                                        }
                                    }
        );


        return root;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer(url);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            if (player != null) {
                playbackPosition = player.getCurrentPosition();
                currentWindow = player.getCurrentWindowIndex();
                playWhenReady = player.getPlayWhenReady();
                player.release();
                player = null;
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if (player != null) {
                playbackPosition = player.getCurrentPosition();
                currentWindow = player.getCurrentWindowIndex();
                playWhenReady = player.getPlayWhenReady();
                player.release();
                player = null;
            }
        }
    }


    private void initializePlayer(String s) {
        if (player == null) {
            TrackSelection.Factory adaptiveTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(getActivity()),
                    new DefaultTrackSelector(adaptiveTrackSelectionFactory), new DefaultLoadControl());
            exoPlayerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
        }
        MediaSource mediaSource = buildMediaSource(Uri.parse(s));
        player.prepare(mediaSource, true, false);

    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }


}
