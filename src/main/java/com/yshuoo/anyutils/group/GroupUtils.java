package com.yshuoo.anyutils.group;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangshuo
 * @date 2019/3/25 10:07
 */
@Component
@Slf4j
public class GroupUtils {

    public static<T> ArrayList<ArrayList<T>> group (List<T> holders, int limitSize){

        int totalCount = holders.size();
        // 计算总共批次数
        int batchCount = (totalCount - 1) / limitSize + 1;
        ArrayList<ArrayList<T>> result = new ArrayList<>(batchCount);
        // 计算每个批次计算的数量，避免某个批次的量过少
        int batchSize = (totalCount - 1) / batchCount + 1;
        for (int index = 0; index < batchCount; ++index){
            int start = index * batchSize;
            int end = Math.min((index + 1) * batchSize, totalCount);
            if (start < end){
                ArrayList list = new ArrayList<>();
                List<T> batchList = holders.subList(start, end);
                list.addAll(batchList);
                result.add(list);
            }
        }
        return result;
    }
}
