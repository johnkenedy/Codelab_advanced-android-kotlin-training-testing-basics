package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class StatisticsUtilsTest {
    @Test
    //fun subjectUnderTest_actionOrInput_resultState
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        //Given
        val tasks = listOf<Task>(
            Task("Title1", "desc1", isCompleted = false),
            Task("Title2", "desc2", isCompleted = false)
        )
        //When
        val result = getActiveAndCompletedStats(tasks)
        //Then
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        //When
        val result = getActiveAndCompletedStats(emptyList())
        //Then
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        //When
        val result = getActiveAndCompletedStats(null)
        //Then
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_both_returnsTwentyEighty() {
        //Given
        val tasks = listOf<Task>(
            Task("Title1", "desc1", isCompleted = false),
            Task("Title2", "desc2", isCompleted = true),
            Task("Title3", "desc3", isCompleted = false),
            Task("Title4", "desc4", isCompleted = false),
            Task("Title5", "desc5", isCompleted = false)
        )
        //When
        val result = getActiveAndCompletedStats(tasks)
        //Then
        assertThat(result.completedTasksPercent, `is`(20f))
        assertThat(result.activeTasksPercent, `is`(80f))
    }

    @Test
    fun getActiveAndCompletedStats_noActive_returnsZerosHundred() {
        //Given
        val task = listOf<Task>(
            Task("Title1", "desc1", isCompleted = true), Task("Title2", "desc2", isCompleted = true)
        )
        //When
        val result = getActiveAndCompletedStats(task)
        //Then
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_oneCompleted_returnsHundredZeros() {
        //Given
        val task = listOf<Task>(
            Task("Title1", "desc1", isCompleted = true)
        )
        //When
        val result = getActiveAndCompletedStats(task)
        //Then
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_oneActive_returnsZerosHundred() {
        //Given
        val task = listOf<Task>(
            Task("Title1", "desc1", isCompleted = false)
        )
        //When
        val result = getActiveAndCompletedStats(task)
        //Then
        assertThat(result.activeTasksPercent, `is`(100f))
    }

}