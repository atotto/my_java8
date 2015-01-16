var Assert = Java.type('org.junit.Assert');
var CoreMatchers = Java.type('org.hamcrest.CoreMatchers');

Assert.assertThat(1, CoreMatchers.is(1));

