package aly.riv.workoutbot

// Contained types of exercises
enum class ExerciseType{
    ARMS_SHOULDERS,
    BACK,
    BURNOUT,
    CORE_FRONT, CORE_SIDE, CORE_BACK,
    FULL_BODY_BODYWEIGHT,
    FULL_BODY_WITH_WEIGHT,
    LEGS_GLUTES,
    WARM_UP
}

// One exercise; like bicep curls. Has an exerciseType to describe what it works
data class Exercise(var name: String = "empty", var type: ExerciseType = ExerciseType.WARM_UP)

// set of exercises grouped together. None have the same type
class SuperSet(val size: Int) {
    var exercises = mutableListOf<Exercise>()
    var fullSet : Boolean = false

    // adds exercise to list and returns true if exersise of same type not already in list
    fun add(exercise: Exercise){
        if ((exercises.size < size) && typeNotInSuperSet(exercise.type)) {
            exercises.add(exercise)
        }
        if (exercises.size == size) {
            fullSet = true
        }
        //throw Exception("SuperSet already full: size $size. Exercise ${exercise.name} failed to add")

    }

    fun typeNotInSuperSet(exerciseType: ExerciseType) : Boolean {
        val ex = exercises.find{it.type == exerciseType}
        return ex == null
    }


}

class WorkoutPlan(
    val numSuperSets: Int,
    var superSets: MutableList<SuperSet> = mutableListOf<SuperSet>(),
    var burnout: Exercise? = null
){

    fun addSuperSet(superSet: SuperSet) {
        if (superSets.size < numSuperSets){
            superSets.add(superSet)
        } else throw Exception("WorkoutPlan already full; SuperSet not added")
    }

    fun addBurnout(exercise: Exercise){
        burnout = exercise
    }

    //todo read out method for style, personality

}

