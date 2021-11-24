package com.twobuffers.wire.utils

// https://stackoverflow.com/questions/34122450/how-to-get-generic-parameter-class-in-kotlin/34123047#34123047
inline val <reified T : Any>T.logTag get() = T::class.java.simpleName
