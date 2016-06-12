package org.pojoxml.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUtils {
  
  private MapUtils() {}
  
  public static List getKeysFromValue(Map hm,Object value){
    Set ref = hm.keySet();
    Iterator it = ref.iterator();
    List list = new ArrayList();

    while (it.hasNext()) {
      Object o = it.next(); 
      if(hm.get(o).equals(value)) { 
        list.add(o); 
      } 
    } 
    return list;
  }

  public static void main(String[] args) {
    Map map = new HashMap();
    map.put("1","Homer");
    map.put("2","Marge");
    map.put("3","Bart");
    map.put("4","Maggie");
    map.put("5","Bart");
  
   }
}

