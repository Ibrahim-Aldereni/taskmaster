package com.example.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TasksViewHolder> {
    // set the list that the adapter will use to bind data to the fragment
    List<Task> allTasks = new ArrayList<>();

    // constructor

    public TaskAdapter(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    // creating view holder class
    public static class TasksViewHolder extends RecyclerView.ViewHolder {

        //task object
        public Task task;
        //view object
        public View itemView;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToTaskDetails = new Intent(v.getContext(), TaskDetailActivity.class);
                    goToTaskDetails.putExtra("task", task.title+' '+task.body);
                    v.getContext().startActivity(goToTaskDetails);
                }
            });
        }
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create the view
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_task_item, viewGroup, false);
        TasksViewHolder tasksViewHolder = new TasksViewHolder(view);
        return tasksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TasksViewHolder tasksViewHolder, int i) {
        tasksViewHolder.task = allTasks.get(i);

        TextView title = tasksViewHolder.itemView.findViewById(R.id.taskTitleFragment);
        TextView body = tasksViewHolder.itemView.findViewById(R.id.taskBodyFragment);
        TextView state = tasksViewHolder.itemView.findViewById(R.id.taskStateFragment);

        title.setText(tasksViewHolder.task.title);
        body.setText(tasksViewHolder.task.body);
        state.setText(tasksViewHolder.task.state);
    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }
}
