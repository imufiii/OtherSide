package mufeed.example.otherside

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mufeed.example.otherside.databinding.ItemProjectBinding

class ProjectAdapter(private val projectList: List<Project>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(project: Project)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projectList[position], listener)
    }

    override fun getItemCount(): Int = projectList.size

    class ProjectViewHolder(private val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project, listener: OnItemClickListener) {
            binding.projectName.text = project.name
            binding.projectDescription.text = project.description
            binding.projectGithubLink.text = project.githubLink

//          loadProfilePicture(binding.profilePicture, project.profilePictureUrl)

            binding.root.setOnClickListener {
                listener.onItemClick(project)
            }

            binding.projectGithubLink.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(project.githubLink))
                it.context.startActivity(intent)
            }
        }
    }
}
