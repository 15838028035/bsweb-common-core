package com.lj.app.core.common.pagination;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class PageToolTest {

  @Before
  public void setUp() {
    PageTool pageTool = new PageTool();
  }

  @Test
  public void pageToJsonJQGridTest() {
    List<TestClass1> TestClass1 = new ArrayList<TestClass1>();

    Page page = new Page(20);
    page.setPageNumber(10);
    page.setTotalCount(100);
    page.setResult(TestClass1);

    String result = PageTool.pageToJsonJQGrid(page);
    assertEquals("{\"total\":5,\"page\":10,\"records\":100,\"rows\":[]}", result);
  }

  @Test
  public void pageToJsonJQGridMapTest() {
    List<Map<String, String>> testClassList = new ArrayList<Map<String, String>>();
    Map<String, String> testMap = new HashMap<String, String>();
    testMap.put("key", "value");
    testClassList.add(testMap);
    Page page = new Page(20);
    page.setPageNumber(10);
    page.setTotalCount(100);
    page.setResult(testClassList);

    String result = PageTool.pageToJsonJQGrid(page);
    assertEquals("{\"total\":5,\"page\":10,\"records\":100,\"rows\":[{\"key\":\"value\"}]}", result);
  }

  private class TestClass1 {

  }
}
