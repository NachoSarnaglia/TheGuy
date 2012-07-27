/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.argeniss;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class Main {

	public static void main(String[] args)
    {
        if (args.length < 1) {
            System.out.println("Usage: java twitter4j.examples.user.ShowUser [screen name]");
            System.exit(-1);
        }
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.showUser(args[0]);
            
            if (user.getStatus() != null) {
            	
                IDs friendsIDs = twitter.getFriendsIDs(user.getId(), -1);
                
                long[] a = friendsIDs.getIDs();
                
                //long id15 = ids.getIDs()[15];
                System.out.println(
                		"User: @" + user.getScreenName()
                		+ " - " + user.getStatus().getText()
                		+ " - "	+ user.getStatus().getRetweetCount() + "ReTweets");                
                
                System.out.println("Statuses count: " + user.getStatusesCount());
                System.out.println("Following count: " + user.getFriendsCount());
                System.out.println("Followers count: " + user.getFollowersCount());
                
                
                int i=0;
                for(; i<a.length; i++)
                {
                	User friendUser = twitter.showUser(a[i]);
                	
                	//Prints user id and user name
                	System.out.println(a[i] + " - " + friendUser.getScreenName());
                }
                System.out.println("Users fetched: " + i);
                
            } else {
                // the user is protected
                System.out.println("@" + user.getScreenName());
            }
           
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to delete status: " + te.getMessage());
            System.exit(-1);
        }
    }
}
