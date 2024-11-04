package com.example.movieposter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Activity class that handles the UI for displaying movie posters and managing the watchlist.
 */
public class MainActivity extends AppCompatActivity implements PostersListener {

    private Button buttonAddToWatchlist;

    /**
     * Called when the activity is starting. This is where most initialization should go:
     * calling {@code setContentView(int)} to inflate the activity's UI, using {@code findViewById(int)}
     * to programmatically interact with widgets in the UI.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView postersRecyclerView = findViewById(R.id.postersRecyclerView);
        buttonAddToWatchlist = findViewById((R.id.buttonAddToWatchlist));

        /**
         * Populates the provided list with sample posters for display in the UI.
         * posterList The list to be populated with Poster objects.
         */
        List<Poster> posterList = new ArrayList<>();
        Poster HarryPotter = new Poster();
        HarryPotter.image = R.drawable.download1;
        HarryPotter.name = "Harry Potter And The Sorcerer's Stone";
        HarryPotter.createdBy = "J.K Rowling";
        HarryPotter.rating = 5f;
        HarryPotter.story = "Harry Potter finds The Sorcerer's Stone";
        posterList.add(HarryPotter);

        Poster starWars = new Poster();
        starWars.image = R.drawable.download2;
        starWars.name = "Star Wars: The Empire Strikes Back";
        starWars.createdBy = "George Lukas";
        starWars.rating = 5f;
        starWars.story = "The Empire goes grr and strikes back";
        posterList.add(starWars);

        Poster Shrek = new Poster();
        Shrek.image = R.drawable.download3;
        Shrek.name = "Shrek";
        Shrek.createdBy = "Dreamworks";
        Shrek.rating = 5f;
        Shrek.story = "Shrek saves princess";
        posterList.add(Shrek);

        Poster blond = new Poster();
        blond.image = R.drawable.download4;
        blond.name = "Legally Blond";
        blond.createdBy = "Reese Witherspoon";
        blond.rating = 5f;
        blond.story = "She is no longer illegally blond";
        posterList.add(blond);

        Poster fourty = new Poster();
        fourty.image = R.drawable.download5;
        fourty.name = "40 Year Old Virgin";
        fourty.createdBy = "Steve Carell";
        fourty.rating = 5f;
        fourty.story = "Literally me.";
        posterList.add(fourty);

        Poster toofast = new Poster();
        toofast.image = R.drawable.download6;
        toofast.name = "2 fast 2 furious";
        toofast.createdBy = "Family";
        toofast.rating = 5f;
        toofast.story = "zoooomin";
        posterList.add(toofast);

        Poster RevengeOfTheSith = new Poster();
        RevengeOfTheSith.image = R.drawable.download7;
        RevengeOfTheSith.name = "Star Wars: Revenge of the Sith";
        RevengeOfTheSith.createdBy = "George Lucas";
        RevengeOfTheSith.rating = 5f;
        RevengeOfTheSith.story = "Anikin goes crazy and produces sick lightsaber battles.";
        posterList.add(RevengeOfTheSith);

        Poster groove = new Poster();
        groove.image = R.drawable.download8;
        groove.name = "The Emperors new Groove";
        groove.createdBy = "Disney";
        groove.rating = 5f;
        groove.story = "An emperor turns into a lama";
        posterList.add(groove);

        Poster pirates = new Poster();
        pirates.image = R.drawable.download9;
        pirates.name = "Pirates of The Carribbean: At Worlds End";
        pirates.createdBy = "Disney";
        pirates.rating = 5f;
        pirates.story = "A prirate is at the worlds end!";
        posterList.add(pirates);

        Poster luca = new Poster();
        luca.image = R.drawable.download10;
        luca.name = "Luca";
        luca.createdBy = "Pixar and Disney";
        luca.rating = 5f;
        luca.story = "Luca is a little fishy";
        posterList.add(luca);

        /**
         * Sets up the adapter for the RecyclerView and establishes a listener for the 'Add to Watchlist' button.
         */
        final PosterAdapter posterAdapter= new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

        /**
         * Sets a click listener on the 'Add to Watchlist' button. This listener constructs a list of selected
         * poster names and displays them using a Toast message.
         */
        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectPosters =  posterAdapter.getSelectedPosters();
                StringBuilder posterNames = new StringBuilder();
                for(int i =0; i<selectPosters.size();i++){
                    if(i==0){
                        posterNames.append(selectPosters.get(i).name);
                    }else{
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this,posterNames.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Handles visibility of the 'Add to Watchlist' button based on whether any posters are selected.
     * The button is made visible if at least one poster is selected, otherwise it is hidden.
     * @param isSelected true if any posters are currently selected, otherwise false.
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        }else{
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}