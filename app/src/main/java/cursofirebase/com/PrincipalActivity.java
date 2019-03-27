package cursofirebase.com;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    List<Notes> notesList;
    FloatingActionButton fab;
    //NotasAdapter adapter;
    DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<Notes,NotasAdapter.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity);
        //createData();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //adapter = new NotasAdapter(this,notesList);
        adapter = new FirebaseRecyclerAdapter<Notes, NotasAdapter.ViewHolder>(
                Notes.class,
                R.layout.notas,
                NotasAdapter.ViewHolder.class,
                databaseReference.child("Listas")
        ) {
            @Override
            protected void populateViewHolder(NotasAdapter.ViewHolder viewHolder, Notes model, final int position) {
                viewHolder.count.setText(String.valueOf(model.getCount()));
                viewHolder.name.setText(model.getName());
                viewHolder.trash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.getRef(position).removeValue();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == recyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown()) {
                    fab.hide();
                }
            }
        });

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(PrincipalActivity.this, InsertNoteActivity.class);
                startActivity(intent);
                break;
        }
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
