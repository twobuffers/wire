package com.twobuffers.wire.utils

// https://stackoverflow.com/questions/34122450/how-to-get-generic-parameter-class-in-kotlin/34123047#34123047
inline val <reified T : Any>T.className get() = T::class.java.simpleName
inline val <reified T : Any>T.logTag get() = T::class.java.simpleName

// Create 'address' of the object, an alternative to `toString` method.
// There the 'address' is class name combined with the instance memory address.
// Example result: `MainActivity@11ed7e8`. It is handy for printing objects to stdout.
inline val <T : Any> T.address get(): String =
    "${this::class.java.simpleName}@${hashCode().toString(16).padStart(8, '0')}"
