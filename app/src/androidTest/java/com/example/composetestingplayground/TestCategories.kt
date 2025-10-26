package com.example.composetestingplayground

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GreetingTest::class
)
class TestGreetingText

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CounterAppTest::class
)
class TestCounterApp

@RunWith(Suite::class)
@Suite.SuiteClasses(
    UserFormTest::class
)
class TestUserForm