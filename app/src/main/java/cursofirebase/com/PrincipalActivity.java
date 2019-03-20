package cursofirebase.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Notes> notesList;
    NotasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity);
        //createData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new NotasAdapter(this,notesList);
        recyclerView.setAdapter(adapter);
    }

    /*public void createData() {
        notesList = new ArrayList<>();
        notesList.add(new Notes("001","Calabazas", 6));
        notesList.add(new Notes("002","Calabazas", 12));
        notesList.add(new Notes("003","Calabazas", 645));
        notesList.add(new Notes("004","Calabazas", 44));
        notesList.add(new Notes("005","Calabazas", 23));
        notesList.add(new Notes("006","Calabazas", 76));
        notesList.add(new Notes("007","Calabazas", 7));
        notesList.add(new Notes("008","Calabazas", 9));
        notesList.add(new Notes("009","Calabazas", 34));
        notesList.add(new Notes("010","Calabazas", 87));
        notesList.add(new Notes("011","Calabazas", 343));
        notesList.add(new Notes("012","Calabazas", 2));
    }*/
}
