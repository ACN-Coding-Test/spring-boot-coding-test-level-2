package com.accenture.codingtest.springbootcodingtest.constants;

import java.util.Arrays;
import java.util.List;


public final class TaskStatus {

    public static final List<String> STATUSES = Arrays.asList("NOT_STARTED", "IN_PROGRESS", "READY_FOR_TEST", "COMPLETED");

    public static final String NOT_STARTED = "NOT_STARTED";

    private TaskStatus() {

    }
}