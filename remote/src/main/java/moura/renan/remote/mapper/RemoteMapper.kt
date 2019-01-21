package moura.renan.remote.mapper

interface RemoteMapper<in M, out E> {
    fun mapFromModel(model: M): E
}