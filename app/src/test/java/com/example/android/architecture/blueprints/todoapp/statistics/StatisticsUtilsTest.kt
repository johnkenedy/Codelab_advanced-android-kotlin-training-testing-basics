package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {
    @Test
    //fun subjectUnderTest_actionOrInput_resultState
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {

        //Given
        val tasks = listOf<Task>(
            Task("Title1", "desc1", isCompleted = true),
            Task("Title2", "desc2", isCompleted = true)
        )

        //When
        val result = getActiveAndCompletedStats(tasks)

        //Then
        assertThat(result.completedTasksPercent, `is` (100f))
        assertThat(result.activeTasksPercent, `is` (0f))
    }

}