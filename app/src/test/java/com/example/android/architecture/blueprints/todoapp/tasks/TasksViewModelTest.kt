package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    // Fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    // Subject under Test
    lateinit var tasksViewModel: TasksViewModel

    // Executes each task synchronously using Architecture Components
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        tasksViewModel = TasksViewModel(tasksRepository)
    }

    @Test
    fun addNewTask_setNewTaskEvent() {
        //When adding a new task
        tasksViewModel.addNewTask()
        //Then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))

    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {
        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)
        // Then the "Add task" action is visible
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }

    @Test
    fun setFilterActiveTasks_tasksAddViewInvisible() {
        // When the filter type is ACTIVE_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ACTIVE_TASKS)
        // Then the "Add task" is invisible
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(false))
    }

    @Test
    fun setFilterCompletedTasks_taskAddViewInvisible() {
        // When the filter type is COMPLETED_TASKS
        tasksViewModel.setFiltering(TasksFilterType.COMPLETED_TASKS)
        // Then the "Add task" is invisible
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(false))
    }

}

//To test LiveData it's recommended you do two things:
//1.Use InstantTaskExecutorRule
//2.Ensure LiveData observation