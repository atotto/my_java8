package logger_test

import (
	"fmt"
	"testing"
)

type Level int8

const (
	ALL Level = 1 << iota
	FINE
	INFO
	DEBUG
	ERROR
)

type Logger struct {
	level   Level
	name    string
	logList []string
}

func getLogger(name string) *Logger {
	return &Logger{level: ALL, name: name}
}

func (l *Logger) log(level Level, message func() string) {
	l.logList = append(l.logList, message())
}

func (l *Logger) logIf(level Level, condition func() bool, message func() string) {
	if l.isLoggable(level) && condition() {
		l.log(level, message)
	}
}

func (l *Logger) isLoggable(level Level) bool {
	return level > l.level
}

// test

func TestLog(t *testing.T) {
	logger := getLogger("mypackage")

	a := []int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}

	for i := 0; i < len(a); i++ {
		logger.logIf(INFO, func() bool { return i == 10 }, func() string { return fmt.Sprintf("a[10]=%d", a[10]) })
	}

	if len(logger.logList) != 1 {
		t.Fatal("want 1, got", len(logger.logList))
	}
	if logger.logList[0] == "a[10] = 10" {
		t.Fatal("want 1, got", logger.logList[0])
	}
}
