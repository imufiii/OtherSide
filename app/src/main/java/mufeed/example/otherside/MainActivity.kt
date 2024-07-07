package mufeed.example.otherside

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import mufeed.example.otherside.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProjectAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupBottomNavigation()
    }

    private fun setupRecyclerView() {
        val projectList = listOf(
            Project("Project 1", "Description 1", "https://github.com/user/project1"),
            Project("Project 2", "Description 2", "https://github.com/user/project2")
        )

        val adapter = ProjectAdapter(projectList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }



    override fun onItemClick(project: Project) {
        if (project.name == "Project 1" || project.name == "Project 2") {
            showJoinProjectDialog()
        }
    }
    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setItemIconTintList(null)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.nav_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    true
                }
                R.id.nav_add -> {
                    startActivity(Intent(this, AddActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
    private fun showJoinProjectDialog() {

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Join IoT Project?")
            .setMessage("Do you want to join the project to work on IoT together?")
            .setPositiveButton("Join") { _, _ ->

                startActivity(Intent(this, AddActivity::class.java))
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }
}

