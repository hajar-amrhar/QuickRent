package com.example.findrent.Fragment;

import androidx.fragment.app.Fragment;


public class vosAnnoncesFragment extends Fragment {
/*
    private RecyclerView recyclerViewHome;
    private AnnAdapt2 annadpter;
    private List<annonce> annoncelist;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //mDatabase = FirebaseDatabase.getInstance();
    // mDb = mDatabase.getReference();
    FirebaseUser user = mAuth.getCurrentUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_vos_annonces, container, false);

        // Inflate the layout for this fragment


        // recyclerViewHome=getActivity().findViewById(R.id.recycler_view_home);

        recyclerViewHome=v.findViewById(R.id.recycler_view_vosannonce);
        recyclerViewHome.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewHome.setLayoutManager(linearLayoutManager);
        annoncelist=new ArrayList<>();
        annadpter=new AnnAdapt2(annoncelist, getContext());
        recyclerViewHome.setAdapter(annadpter);
        // Inflate the layout for this fragment

        readAnnonce();

        return v;
    }


    private void readAnnonce(){
        FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("vos annonces").addValueEventListener(new ValueEventListener(){

        //DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Annonce");


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                annoncelist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    annonce Annonce=snapshot.getValue(annonce.class);
                    annoncelist.add(Annonce );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

 */
}